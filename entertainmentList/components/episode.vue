<template>
	<view>
		<scroll-view class="tab-bar" :scroll="false" :scroll-x="true" :show-scrollbar="false">
			<view style="flex-direction: column;">
				<view style="flex-direction: row;">
					<view class="uni-tab-item" v-for="(tab,index) in list" :key="tab" @tap="episode">
						<text class="uni-tab-item-title"
							:class="tabIndex==index ? 'uni-tab-item-title-active' : ''">{{index}}</text>
					</view>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	import req from "../api/request.js";
	export default {
		props:{
			list:{
				type:Array,
				default:[]
			},
			horizontal:{
				type:Boolean,
				default:false
			}
		},
		data() {
			return {
			}
		},
		created() {
		},
		methods:{
			episode(index) {
				req.cancelHttp()
				uni.redirectTo({
					url: `/pages/amineDetails/amineDetails?index=${index}&id=${this.info.id}&type=${this.type}`
				})
			},
		}
	}
</script>

<style scoped>

	.tab-bar {
		width: 750rpx;
		height: 84rpx;
		flex-direction: row;
		/* #ifndef APP-PLUS */
		white-space: nowrap;
		/* #endif */
	}

	.uni-tab-item {
		/* #ifndef APP-PLUS */
		display: inline-block;
		/* #endif */
		flex-wrap: nowrap;
		padding-left: 25px;
		padding-right: 25px;
	}
	.uni-tab-item-c{
		/* #ifndef APP-PLUS */
		display: inline-block;
		/* #endif */
		flex-wrap: wrap;
		width: 50px;
		height: 50px;
	}
</style>