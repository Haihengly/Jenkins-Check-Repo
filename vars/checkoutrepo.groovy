def call(Map config) {
    def CLONE_DIR = "/My-Docker"

    sh """
        git clone ${config.repoUrl} ${CLONE_DIR}/${config.BUILD_DIR}
        ls -l ${CLONE_DIR}/${config.BUILD_DIR}
    """
}