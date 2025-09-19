def call(Map config) {
    def CLONE_DIR = "${env.HOME}/My-Docker"

    sh """
        if [ -d "${CLONE_DIR}/${config.BUILD_DIR}/App-clone/.git" ]; then
            cd ${CLONE_DIR}/${config.BUILD_DIR}/App-clone && git fetch --all && git reset --hard origin/${config.branch}
        else
            git clone -b ${config.branch} ${config.repoUrl} ${CLONE_DIR}/${config.BUILD_DIR}/App-clone
        fi

        # Ensure only app code is refreshed, Dockerfile/compose stay safe
        ls -l ${CLONE_DIR}/${config.BUILD_DIR}/App-clone
    """
}
