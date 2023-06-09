<template>
  <div>
    <h1>Product List</h1>
    <div v-for="(product, index) in products" :key="index" @click="goToProduct(product.productId)">
      <div>ProductId: {{ product.productId }}</div>
      <div>Name: {{ product.name }}</div>
      <div>Price: {{ product.price }}</div>
      <div>Amount: {{ product.amount }}</div>
      <hr>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      products: [],
      memberId: 1 // memberId를 저장하는 공간입니다. 이 부분은 로그인 부분과 연동되어야 할 것입니다.
    }
  },
  async created () {
    try {
      const response = await fetch('/order/product/list', { // 요청 주소를 적절히 수정해야 합니다.
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      })
      if (!response.ok) {
        throw new Error('HTTP error ' + response.status)
      }
      const data = await response.json()
      this.products = data.body
    } catch (error) {
      console.error('Error:', error)
      alert('상품 불러오기 중 오류가 발생하였습니다.')
    }
  },
  methods: {
    goToProduct (productId) {
      this.$router.push(`/product/${productId}?memberId=${this.memberId}`)
    }
  }
}
</script>
