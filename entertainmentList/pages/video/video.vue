<template>
	<view class="container">
		<view :style="'height:'+tabHeight+'px'"></view>
		<view id="mse"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				tabHeight:0,
				width:0,
				height:0
			}
		},
		onLoad() {
			uni.getSystemInfo({
				success: (res) => {
					this.tabHeight = res.statusBarHeight
					this.width = res.screenWidth
					this.height = res.screenHeight
				}
			})
		},
		methods: {
			handleMessage(e){
				if(e.detail.data[0].isFull){
					plus.screen.lockOrientation('landscape');
				}else{
					plus.screen.lockOrientation('portrait-primary');
				}
			}
		}
	}
</script>

<style>
</style>