<template>
  <div class="container" style="height: 100%">
  <div class="overflow-auto">
    <b-table striped hover :items="rules"></b-table>
    <b-pagination-nav :link-gen="linkGen" :number-of-pages="ruleGroupTotalPage" use-router></b-pagination-nav>
  </div>
  </div>
</template>

<script>
import DataService from "@/service/DataService";

export default {
  data(){
    return {
      ruleGroupTotalPage: 0,
      rules:[]
    }
  },
  methods:{
    linkGen(pageNum){
      console.log(pageNum);

      return '';
    },
    getRuleGroup(pageNum){
      console.log(pageNum);
      DataService.getRuleGroups().then(response => {
        console.log(response);
      });
    }
  },
  mounted() {
    this.getRuleGroup(this.ruleGroupTotalPage);
  }
}
</script>

<style scoped>

</style>