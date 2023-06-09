<template>
  <div>
    <h1>회원가입</h1>
    <form @submit.prevent="submitForm">
      <div>
        <label for="email">이메일:</label>
        <input type="email" id="email" v-model="email" required />
      </div>
      <div>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" v-model="password" required minlength="10" />
      </div>
      <div>
        <button type="submit">가입하기</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    async submitForm () {
      const url = '/order/user/register'
      const data = {
        email: this.email,
        password: this.password
      }
      try {
        const response = await fetch(url, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        })
        if (!response.ok) {
          throw new Error('HTTP error ' + response.status)
        }
        const responseBody = await response.json() // 여기를 추가
        this.memberId = responseBody.body // 가정: 응답의 body 필드에 memberId가 있다.
        alert('회원가입이 완료되었습니다.')
        this.$router.push('/product') // 상품 페이지로 이동
      } catch (error) {
        console.error('Error:', error)
        alert('회원가입 중 오류가 발생하였습니다.')
      }
    }
  }
}
</script>
