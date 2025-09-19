def call(Map config) { 
    def STORE_DIR = "${env.HOME}/My-Docker"

    echo "Building Docker images with docker-compose..."
    sh """
        cd ${STORE_DIR}/${config.BUILD_DIR}
        docker compose version
        docker compose build --build-arg VERSION=${config.version} my-store
    """
}