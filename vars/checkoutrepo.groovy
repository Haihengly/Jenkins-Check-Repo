def call(Map config) {
    def CLONE_DIR = "/My-Docker/Dev-Service"

    sh """
        rm -rf ${CLONE_DIR}/${config.BUILD_DIR} 
        pwd
        ls -la ${CLONE_DIR}/
        git clone -b ${config.branch} ${config.REPO_URL} ${CLONE_DIR}/${config.BUILD_DIR}
        ls -la ${CLONE_DIR}/${config.BUILD_DIR}
    """

}
