def call(Map config) {
    def STORE_DIR = "/My-Docker/"

    echo 'Starting containers...'
    sh """
        cd ${STORE_DIR}/${config.BUILD_DIR}
        docker compose up -d
    """

}
 