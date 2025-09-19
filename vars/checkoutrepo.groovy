def call(Map config) {
    def CLONE_DIR = "/var/jenkins_home/My-Docker/Dev-Service"

    sh """
        if [ -d "${CLONE_DIR}/.git" ]; then
            cd ${CLONE_DIR} && git fetch --all && git reset --hard origin/${config.branch}
        else
            rm -rf ${CLONE_DIR}
            git clone -b ${config.branch} ${config.repoUrl} ${CLONE_DIR}
        fi
        ls -l ${CLONE_DIR}
    """
}
