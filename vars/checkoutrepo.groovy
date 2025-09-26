def call(Map config) {

    dir("${env.WORKSPACE}/.scm-detect") {
        git branch: config.branch, url: config.REPO_URL
    }

    def CLONE_DIR = "/My-Docker/Dev-Service"

    sh """
        rm -rf ${CLONE_DIR}/${config.BUILD_DIR} 
        pwd
        ls -la ${CLONE_DIR}/
        git clone -b ${config.branch} ${config.REPO_URL} ${CLONE_DIR}/${config.BUILD_DIR}
        ls -la ${CLONE_DIR}/${config.BUILD_DIR}
    """

}
