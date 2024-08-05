<template>
	<view>
		miveList
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list:[],
				total:1,
				pageSize:10,
				pageNum:1,
				keyWord:"",
				status:"loadmore"
			}
		},
		onLoad() {
			this.getMoveList()
		},
		methods: {
			async getMoveList(){
				const res = await this.$http.getMoveList({data:{pageNum:this.pageNum,pageSize:this.pageSize,title:this.keyWord}})
				this.list = [...this.list,...res.rows]
				this.total = res.total
				this.status = 'loadmore';
				if(this.pageNum * this.pageSize>=this.total) {
					this.status = 'nomore';
				}
				console.log(this.list);
			}
		}
	}
</script>

<style>

</style>
