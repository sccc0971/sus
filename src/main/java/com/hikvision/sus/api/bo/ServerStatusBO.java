package com.hikvision.sus.api.bo;

public class ServerStatusBO {

    private OnlineUpgradeServer onlineUpgradeServer;

    class OnlineUpgradeServer {

        private boolean connectStatus = true;

        public boolean isConnectStatus() {
            return connectStatus;
        }

        public void setConnectStatus(boolean connectStatus) {
            this.connectStatus = connectStatus;
        }
    }

    public ServerStatusBO(){
        this.onlineUpgradeServer = new OnlineUpgradeServer();
    }

    public ServerStatusBO.OnlineUpgradeServer getOnlineUpgradeServer() {
        return onlineUpgradeServer;
    }

    public void setOnlineUpgradeServer(OnlineUpgradeServer onlineUpgradeServer) {
        this.onlineUpgradeServer = onlineUpgradeServer;
    }
}
