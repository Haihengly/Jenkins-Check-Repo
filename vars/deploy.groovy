def call(Map config) {
    def STORE_DIR = "/My-Docker/Dev-Service/${config.FOLDER}-${config.envName}"
    def envFile = config.envName == "main" ? ".env.main" : ".env.dev"
    def composeFiles = "-f docker-compose.base.yml -f docker-compose.${config.envName}.yml"

    echo 'Starting containers...'
    sh """
        cd ${STORE_DIR}
        export BUILD_DIR=${config.FOLDER}-${config.envName}
        docker compose ${composeFiles} --env-file ${envFile} down
        docker compose ${composeFiles} --env-file ${envFile} up -d
    """
}
 