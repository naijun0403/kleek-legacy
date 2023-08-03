package app.kleek.reflow.root

object RootService {

    /**
     * Allow root permission
     *
     * kleek은 기본적으로 root 권한이 필요합니다.
     *
     * @return true if success
     */
    fun allowRootPermission(): Boolean {
        return try {
            Runtime.getRuntime().exec("su -c mkdir /data/system/kleek").waitFor()
            Runtime.getRuntime().exec("su -c chmod 777 /data/system/kleek").waitFor()

            true
        } catch (e: Exception) {
            false
        }
    }

}