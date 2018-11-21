package com.hikvision.sus.api.controller;

import com.hikvision.sus.Application;
import com.hikvision.sus.api.assist.RespStatus;
import com.hikvision.sus.api.assist.RespStatusEnum;
import com.hikvision.sus.api.bo.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.apache.commons.lang3.time.DateFormatUtils.format;

/**
 * 升级服务接口Controller
 *
 * @author shaochunchao
 */
@RestController
@RequestMapping("/ISAPI/VCS/terminals/onlineUpgrade")
public class UpgradeTerminalController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 升级文件夹的名字
     */
    private final static String UPGRADE_FOLDERNAME = "upgrade";

    /**
     * 日志文件夹的名字
     */
    private final static String LOG_FOLDERNAME = "log";

    /**
     * 升级日志文件夹的名字
     */
    private final static String UPGRADE_LOG_FOLDERNAME = "upgrade_result_log";

    /**
     * 升级文件的文件名分隔符
     */
    private final static String SEPARATOR = "_";

    /**
     * 日志文件后缀
     */
    private final static String LOG_FILE_POSTFIX = ".log";

    /**
     * 日志文件时间戳格式
     */
    private final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    private final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 写日志锁
     */
    private final static Lock loglock = new ReentrantLock();

    /**
     * 获取升级服务器状态
     *
     * @return 服务器状态
     * @author shaochunchao
     */
    @GetMapping("/serverStatus")
    public Object serverStatus() {
        return new ServerStatusBO();
    }

    /**
     * 获取可升级版本数据
     *
     * @return 升级文件名
     * @author shaochunchao
     */
    @PostMapping("/judgeVersion")
    public Object judgeVersion(@RequestBody @Validated DevicePacketInfoBO devicePacketInfo, HttpServletRequest request) {
        logger.info("获取升级信息，终端IP：{}，当前版本：{}", request.getRemoteAddr(), devicePacketInfo.getCurrentPacket().getVersion());
        String upgrade_path = Application.applicationHome.concat(File.separator).concat(UPGRADE_FOLDERNAME);
        File upgradeFolder = new File(upgrade_path);
        UpgradePacketInfoBO upgradePacketInfo = new UpgradePacketInfoBO();
        if (upgradeFolder.exists() && upgradeFolder.isDirectory()) {
            File[] files = upgradeFolder.listFiles(pathname -> pathname.isFile()
                    && StringUtils.startsWith(pathname.getName(), devicePacketInfo.getCurrentPacket().getPacketType() + SEPARATOR));
            if (files != null && files.length >= 1) {
                File upgradeFile = files[0];
                String[] names = StringUtils.substringBeforeLast(upgradeFile.getName(), ".").split(SEPARATOR);
                if (names.length >= 2) {
                    UpgradePacketInfoBO.UpgradePacket up = new UpgradePacketInfoBO.UpgradePacket();
                    up.setId(1);
                    up.setDevType(devicePacketInfo.getCurrentPacket().getDevType());
                    up.setPacketType(devicePacketInfo.getCurrentPacket().getPacketType());
                    up.setVersion(names[1]);
                    up.setBuildDate(names[2]);
                    try (FileInputStream fis = new FileInputStream(upgradeFile)) {
                        up.setMd5(DigestUtils.md5Hex(fis));
                    } catch (FileNotFoundException e) {
                        logger.error("计算升级文件md5值时，找不到文件", e);
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        logger.error("计算升级文件md5值时，出现异常", e);
                        throw new RuntimeException(e);
                    }
                    up.setPacketUrl(upgradeFile.getName());
                    up.setRecommendUpgrade(true);
                    UpgradePacketInfoBO.UpgradePacketInfo upgradePacket = new UpgradePacketInfoBO.UpgradePacketInfo();
                    upgradePacket.setUpgradePacket(up);
                    upgradePacketInfo.getUpgradePacketList().add(upgradePacket);
                    log(devicePacketInfo.getCurrentPacket().getIdentityKey(), request.getRemoteAddr(), "noFeedback");
                }
            }
        }
        return upgradePacketInfo;
    }

    /**
     * 获取升级文件流
     *
     * @param response 响应对象
     * @author shaochunchao
     */
    @PostMapping("/upgrade")
    public void upgrade(@RequestBody @Validated UpgradeInfoBO upgradeInfo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("获取升级文件，终端IP：{}", request.getRemoteAddr());
        String upgrade_path = Application.applicationHome.concat(File.separator).concat(UPGRADE_FOLDERNAME);
        File upgradeFolder = new File(upgrade_path);
        if (upgradeFolder.exists() && upgradeFolder.isDirectory()) {
            File[] files = upgradeFolder.listFiles(pathname -> pathname.isFile()
                    && StringUtils.equals(pathname.getName(), upgradeInfo.getUpgradeInfo().getPacketUrl()));
            if (files != null && files.length == 1) {
                try (InputStream inputStream = new FileInputStream(files[0]);
                     OutputStream outputStream = response.getOutputStream()) {
                    response.reset();
                    response.setContentType("application/octet-stream");
                    response.setContentLength((int) files[0].length());
                    String fileName = URLEncoder.encode(files[0].getName(), StandardCharsets.UTF_8.name());
                    fileName = StringUtils.replace(fileName, "+", "%20");
                    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
                    IOUtils.copy(inputStream, outputStream);
                    outputStream.flush();
                } catch (FileNotFoundException e) {
                    logger.error("找不到升级文件", e);
                } catch (IOException e) {
                    logger.error("读取升级文件失败", e);
                }
            }
        }
    }

    /**
     * 升级结果反馈，记录日志
     *
     * @return 返回true
     * @author shaochunchao
     */
    @PostMapping("/status")
    public Object status(@RequestBody @Validated UpgradeResultBO upgradeResult, HttpServletRequest request) {
        logger.info("反馈升级结果，终端IP：{}，结果：{}", request.getRemoteAddr(), upgradeResult.getOnlineUpgradeStatus().getStatus());
        log(upgradeResult.getOnlineUpgradeStatus().getIdentityKey(), request.getRemoteAddr(), upgradeResult.getOnlineUpgradeStatus().getStatus());
        return RespStatus.genStatus(RespStatusEnum.OK);
    }

    /**
     * 日志记录
     *
     * @param identityKey 升级标识
     * @param IP          终端IP
     * @param msg         日志信息
     */
    private void log(String identityKey, String IP, String msg) {
        try {
            loglock.lock();
            String log_path = Application.applicationHome.concat(File.separator).concat(LOG_FOLDERNAME).concat(File.separator).concat(UPGRADE_LOG_FOLDERNAME);
            File logFolder = new File(log_path);
            if (!logFolder.exists() || logFolder.isFile()) {
                try {
                    FileUtils.forceMkdir(logFolder);
                } catch (IOException e) {
                    logger.error("创建升级日志文件夹失败", e);
                }
            }
            File[] files = logFolder.listFiles(pathname -> pathname.isFile() && StringUtils.endsWith(
                    StringUtils.substringBeforeLast(pathname.getName(), "."), SEPARATOR + identityKey));
            File logFile;
            if (files == null || files.length == 0) {
                logFile = new File(log_path + File.separator + format(new Date(), yyyyMMddHHmmss) +
                        SEPARATOR + identityKey + LOG_FILE_POSTFIX);
                try {
                    if (!logFile.createNewFile()) {
                        logger.error("创建升级日志文件失败,path:{0}", logFile.getPath());
                    }
                } catch (IOException e) {
                    logger.error("创建升级日志文件失败,path:{0}", logFile.getPath(), e);
                }
            } else {
                logFile = files[0];
            }
            try {
                List<String> lines = FileUtils.readLines(logFile, StandardCharsets.UTF_8.name());
                boolean exist = false;
                int blankCount = 16 - IP.length();
                StringBuilder IPstr = new StringBuilder(IP);
                for (int i = 0; i < blankCount; i++) {
                    IPstr.append(" ");
                }
                String logmsg = IPstr + format(new Date(), yyyy_MM_dd_HH_mm_ss)
                        + " 升级结果：" + msg;
                for (int i = 0; i < lines.size(); i++) {
                    if (StringUtils.startsWith(lines.get(i), IP + " ")) {
                        exist = true;
                        lines.set(i, logmsg);
                        break;
                    }
                }
                if (!exist) {
                    lines.add(logmsg);
                }
                FileUtils.writeLines(logFile, StandardCharsets.UTF_8.name(), lines);
            } catch (IOException e) {
                logger.error("记录升级日志发生异常", e);
            }
        } finally {
            loglock.unlock();
        }
    }

}
