def call(Map config) { 
    def STORE_DIR = "/My-Docker/Dev-Service"

    echo "Building Docker images with docker-compose..."
    sh """
        cd ${STORE_DIR}
        docker compose version
        docker compose build
    """
}