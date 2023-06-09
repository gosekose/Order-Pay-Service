const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: '/',
  devServer: {
    host: 'localhost',
    port: 3000,
    open: false,
    client: {
      overlay: {
        warnings: true,
        errors: true
      }
    },
    proxy: {
      '/order': {
        target: 'http://localhost:8081',
        pathRewrite: {
          '^/order': ''
        }
      },
      '/pay': {
        target: 'http://localhost:8082',
        pathRewrite: {
          '^/pay': ''
        }
      }
    }
  }
})
