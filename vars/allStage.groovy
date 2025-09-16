// def call(String branch, String repoUrl, Map buildParams = [:]) {
//     return [
//         [name: 'Checkout', action: { checkoutrepo(branch, repoUrl) }],
//         [name: 'Build', action: { build() }],
//         [name: 'Deploy', action: { deploy() }]
//     ]
// }


def call(Map config) {
    return [
        [
            name: 'Checkout',
            action: { ->
                checkoutrepo([
                    branch: config.branch,
                    repoUrl: config.repoUrl
                ])
            }
        ],
        [
            name: 'Build',
            action: { ->
                if (config.build) {
                    buildApp(config)
                } else {
                    echo "Skipping build"
                }
            }
        ],
        [
            name: 'Test',
            action: { ->
                if (config.runTests) {
                    echo "Running tests for ${config.version}"
                    // sh 'npm test' or mvn test
                } else {
                    echo "Skipping tests"
                }
            }
        ],
        [
            name: 'Deploy',
            action: { ->
                if (config.deploy) {
                    deployApp(config)
                } else {
                    echo "Skipping deploy"
                }
            }
        ]
    ]
}

