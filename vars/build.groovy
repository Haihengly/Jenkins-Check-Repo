def call(Map config) { 
    def STORE_DIR = "/My-Docker/"

    echo "Building Docker images with docker-compose..."
    sh """
        cd ${STORE_DIR}/${config.BUILD_DIR}
        docker compose build --build-arg VERSION=${config.version}
    """
}