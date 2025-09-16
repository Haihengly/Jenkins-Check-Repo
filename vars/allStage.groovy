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
            name: 'Checkout Repo Stage',
            action: { -> checkoutrepo(config)
                // checkoutrepo([
                //     branch: config.branch,
                //     repoUrl: config.repoUrl
                // ])
            }
        ],
        [
            name: 'Build Stage',
            action: { -> build(config)
                // if (config.build) { 
                //     build(config)
                // } else {
                //     echo "Skipping build"
                // }
            }
        ],
        [
            name: 'Deploy Stage',
            action: { -> deploy(config)
                // if (config.deploy) {
                //     deploy(config)
                // } else {
                //     echo "Skipping deploy"
                // }
            }
        ]
    ]
}

