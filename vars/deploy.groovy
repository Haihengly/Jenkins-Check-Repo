def call(Map config) {
    def STORE_DIR = "${env.HOME}/My-Docker"

    echo 'Starting containers...'
    sh """
        cd ${STORE_DIR}/${config.BUILD_DIR}
        docker compose up -d
    """

}
 