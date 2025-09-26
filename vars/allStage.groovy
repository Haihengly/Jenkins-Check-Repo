// def call(Map config) {
//     def buildFlag  = config.get('build', true)
//     def deployFlag = config.get('deploy', true)

//     return [
//         [
//             name: 'Checkout',
//             action: { ->
//                 echo "Checking out branch ${config.branch}"
//                 checkoutrepo(config)
//             }
//         ],
//         [
//             name: 'Build',
//             action: { ->
//                 if (buildFlag) {
//                     echo "Building version ${config.version}"
//                     build(config)
//                 } else {
//                     echo "Skipping build stage"
//                 }
//             }
//         ],
//         [
//             name: 'Deploy',
//             action: { ->
//                 if (deployFlag) {
//                     echo "Deploying version ${config.version} to ${config.envName}"
//                     deploy(config)
//                 } else {
//                     echo "Skipping deploy stage"
//                 }
//             }
//         ]
//     ]
// }

def call(Map config) {
    def buildFlag  = config.get('build', true)
    def deployFlag = config.get('deploy', true)

    return [
        [
            name: 'Checkout',
            action: { ->
                echo "Checking out branch ${config.branch}"
                
                // SCM detection in Jenkins workspace
                node {
                    dir("${env.WORKSPACE}/.scm-detect") {
                        deleteDir() // optional: clean old checkout
                        git branch: config.branch, url: config.REPO_URL
                    }
                }

                // Your separate Docker clone
                checkoutrepo(config)
            }
        ],
        [
            name: 'Build',
            action: { ->
                if (buildFlag) {
                    echo "Building version ${config.version}"
                    build(config)
                } else {
                    echo "Skipping build stage"
                }
            }
        ],
        [
            name: 'Deploy',
            action: { ->
                if (deployFlag) {
                    echo "Deploying version ${config.version} to ${config.envName}"
                    deploy(config)
                } else {
                    echo "Skipping deploy stage"
                }
            }
        ]
    ]
}
