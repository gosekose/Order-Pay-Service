<template>
  <div>
    <h1>{{ product.name }}</h1>
    <p>가격: {{ product.price }}</p>
    <p>수량: {{ product.amount }}</p>
    <input v-model="orderAmount" type="number" placeholder="주문 수량을 입력하세요" />
    <button @click="submitOrder">주문하기</button>
  </div>
</template>
<script>
export default {
  data () {
    return {
      product: {},
      orderAmount: 1, // 기본적으로 1개 주문
      memberId: 1 // 회원 ID를 저장하는 공간입니다. 이 부분은 로그인 부분과 연동되어야 할 것입니다.
    }
  },
  async created () {
    try {
      const productId = this.$route.params.productId // 라우트 파라미터에서 상품 ID를 얻습니다.
      const response = await fetch(`/order/product/${productId}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      })
      if (!response.ok) {
        throw new Error('HTTP error ' + response.status)
      }
      const data = await response.json()
      this.product = data.body
    } catch (error) {
      console.error('Error:', error)
      alert('상품 불러오기 중 오류가 발생하였습니다.')
    }
  },
  methods: {
    async submitOrder () {
      try {
        const orderRequest = {
          memberId: this.memberId,
          productId: this.product.productId,
          amount: this.orderAmount
        }
        const response = await fetch('/order/order', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(orderRequest)
        })
        if (response.ok) {
          alert('주문 처리 중')
        } else {
          throw new Error('HTTP error ' + response.status)
        }
        const orderForPay = await response.json()
        // 콜백으로 /pay/pay로 response를 전달
        const payResponse = await fetch('/pay/pay', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(orderForPay)
        })
        if (!payResponse.ok) {
          throw new Error('HTTP error ' + payResponse.status)
        }
        alert('주문이 완료되었습니다.')
      } catch (error) {
        console.error('Error:', error)
        alert('주문 중 오류가 발생하였습니다.')
      }
    }
  }
}
</script>
