def call(Map config) {
    def STORE_DIR = "/home/amhengly/My-Docker/"

    echo 'Starting containers...'
    sh """
        cd ${STORE_DIR}/${config.BUILD_DIR}
        docker compose up -d
    """

}
 