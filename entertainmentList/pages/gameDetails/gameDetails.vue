<template>
	<view class="padding-xs padding-bottom-lg">
		<view class="flex align-stretch justify-between margin-top-sm">
			<image class="cover" :src="info.img" mode="aspectFill"></image>
			<view class="flex flex-direction justify-between" style="width: 420rpx;">
				<view class="text-bold text-xl">{{ info.chTitle }}</view>
				<view class="text text-gray">英文名：{{ info.title }}</view>
				<view class="text text-gray">类型：{{ info.gameType }}</view>
				<view class="text text-gray">发售日期：{{ info.releaseDate }}</view>
				<view class="text text-gray">开发商：{{ info.developer }}</view>
				<view class="text text-gray">大小：{{ info.gameSize }}</view>
				<view class="text-sm text-gray">{{ info.subhnamever }} {{ info.subhname }}</view>
				<view class="text text-gray">评分：{{ info.score }}</view>
			</view>
		</view>
		<view class="margin-top-sm">
			<uni-link :href="info.gameUrl" text="种子下载"></uni-link>
		</view>
		<view class="margin-top-sm">
			<view class="text-lg text-bold">
				游戏简介：
			</view>
			<view class="margin-top-xs">{{ info.desc }}</view>
		</view>
		<view class="margin-top-sm" v-if="info.video">
			<video style="width: 100%;" :src="info.video[1]" controls></video>
		</view>
		<swiper class="swiper" circular :indicator-dots="indicatorDots" style="height: 400rpx;">
			<swiper-item v-for="(item,index) in info.imgList" :key="item" style="height: 400rpx;">
				<view @click="previewImage(index)" class="swiper-item">
					<image style="width: 100%;height: 400rpx;" :src="item" mode=""></image>
				</view>
			</swiper-item>
		</swiper>
		<view class="margin-top-sm">
			<view class="text-lg text-bold">
				配置：
			</view>
			<view class="margin-top-xs" v-html="info.minSpec"></view>
		</view>
	</view>
</template>

<script>
	
	export default {
		data() {
			return {
				info: {},
				indicatorDots: true
			};
		},
		onLoad(option) {
			this.getListDeatils(option.id)
		},
		methods: {
			async getListDeatils(id) {
				const res = await this.$http.getListDeatils(id)
				this.info = res.data
				this.info.video = res.data.movieMax.split(";")
				this.info.imgList = res.data.descImg.split(";")
				this.info.imgHeightList = res.data.descHeightImg.split(";")
				this.info.imgList.pop()
				this.info.imgHeightList.pop()
				this.info.minSpec = this.formatRequirements(this.info.minSpec);
			},
			previewImage(index){
				uni.previewImage({
					urls:this.info.imgHeightList,
					current:index
				})
			},
			download(){
				
			},
			formatRequirements(text) {
				var startIndex1 = this.info.minSpec.indexOf("操作系统：");
				var endIndex1 = this.info.minSpec.indexOf("处理器：");
				var result1 = this.info.minSpec.substring(startIndex1, endIndex1);
				
				var startIndex2 = this.info.minSpec.indexOf("处理器：");
				var endIndex2 = this.info.minSpec.indexOf("显卡：");
				var result2 = this.info.minSpec.substring(startIndex2, endIndex2);
				
				var startIndex3 = this.info.minSpec.indexOf("显卡：");
				var endIndex3 = this.info.minSpec.indexOf("磁盘空间：");
				var result3 = this.info.minSpec.substring(startIndex3, endIndex3);
				
				var startStr = "磁盘空间：";
				var result4 = this.info.minSpec.match(new RegExp(startStr + ".*"));
				
				
				return result1+"<br/>"+result2+"<br/>"+result3+"<br/>"+result4
			}

		}
	}
</script>

<style lang="scss" scoped>
	.cover {
		width: 300rpx;
		height: 400rpx;
	}
</style>