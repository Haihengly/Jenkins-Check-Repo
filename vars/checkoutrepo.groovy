def call(Map config) {
    def CLONE_DIR = "${env.HOME}/My-Docker/${config.BUILD_DIR}"

    sh """
        # If Git repo exists, update it
        if [ -d "${CLONE_DIR}/.git" ]; then
            cd ${CLONE_DIR} && git fetch --all && git reset --hard origin/${config.branch}
        else
            # If folder exists but not a Git repo, move Docker files temporarily
            if [ -d "${CLONE_DIR}" ]; then
                mkdir -p /tmp/docker-backup
                mv ${CLONE_DIR}/Dockerfile /tmp/docker-backup/ 2>/dev/null || true
                mv ${CLONE_DIR}/docker-compose.yml /tmp/docker-backup/ 2>/dev/null || true
                rm -rf ${CLONE_DIR}/*
            fi

            git clone -b ${config.branch} ${config.repoUrl} ${CLONE_DIR}

            # Restore Docker files
            mv /tmp/docker-backup/Dockerfile ${CLONE_DIR}/ 2>/dev/null || true
            mv /tmp/docker-backup/docker-compose.yml ${CLONE_DIR}/ 2>/dev/null || true
        fi

        ls -l ${CLONE_DIR}
    """
}
