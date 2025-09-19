def call(Map config) {
    def CLONE_DIR = "/My-Docker"

    sh """
        if [ -d "${CLONE_DIR}/${config.BUILD_DIR}/.git" ]; then
            cd ${CLONE_DIR}/${config.BUILD_DIR} && git fetch --all && git reset --hard origin/${config.branch}
        else
            git clone -b ${config.branch} ${config.repoUrl} ${CLONE_DIR}/${config.BUILD_DIR}
        fi

        # Ensure only app code is refreshed, Dockerfile/compose stay safe
        ls -l ${CLONE_DIR}/${config.BUILD_DIR}
    """
}
