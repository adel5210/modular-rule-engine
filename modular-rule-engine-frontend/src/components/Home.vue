<template>
  <div class="container" style="height: 100%">
    <div class="overflow-auto" style="padding-top: 20px;">
      <h2>Modular Rule engine</h2>
      <b-button v-b-modal.modal-add-rulegroup>Add new rule group</b-button>
      <b-modal id="modal-add-rulegroup"
               title="New Rule Group">

      </b-modal>
      <b-table
          id="rule-group-table"
          :items="rulesGroup"
          :per-page="numberOfElementsPerPage"
          :current-page="currentPage"
          small
      ></b-table>
      <b-pagination
          v-if="totalElements > 0"
          v-model="currentPage"
          :total-rows="totalElements"
          :per-page="numberOfElementsPerPage"
          aria-controls="rule-group-table"
          @click="getRuleGroup(currentPage)"
          align="center"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import DataService from "@/service/DataService";

export default {
  data() {
    return {
      ruleGroupTotalPage: 0,
      rulesGroup: [],
      numberOfElementsPerPage: 0,
      totalElements: 0,
      currentPage: 1
    }
  },
  methods: {
    getRuleGroup(pageNum) {
      console.log(pageNum);
      DataService.getRuleGroups().then(response => {
        let data = response.data;
        this.ruleGroupTotalPage = data.totalPages;
        this.rulesGroup = data.content;
        this.numberOfElementsPerPage = data.numberOfElements;
        this.currentPage = data.number;
        this.totalElements = data.totalElements;
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