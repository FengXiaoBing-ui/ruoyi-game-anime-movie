const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    proxy:{
      '/api':{
        target:"https://www.libvio.cc",
        changeOrigin:true,
        secure:true,
        pathRewrite:{"^/api": "/"}
      }
    }
  }
})
