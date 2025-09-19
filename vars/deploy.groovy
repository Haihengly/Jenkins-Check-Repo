def call(Map config) {
    def STORE_DIR = "/My-Docker/Dev-Service"

    echo 'Starting containers...'
    sh """
        cd ${STORE_DIR}
        docker compose up -d
    """

}
 