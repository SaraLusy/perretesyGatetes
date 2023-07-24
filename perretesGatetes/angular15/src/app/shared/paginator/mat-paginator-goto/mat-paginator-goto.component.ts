import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-mat-paginator-goto',
  templateUrl: './mat-paginator-goto.component.html',
  styleUrls: ['./mat-paginator-goto.component.scss']
})
export class MatPaginatorGotoComponent implements OnInit{
  pageSize: number = 0;
  pageIndex: number = 0;
  length: number = 0;
  goTo: number = 0;
  pageNumbers: number[] = [];
  //@ViewChild(MatPaginator) paginator: MatPaginator;
  @Input() disabled = false;
  @Input() hidePageSize = false;
  @Input() pageSizeOptions: number[] = [];
  @Input() showFirstLastButtons = false;
  @Output() page = new EventEmitter<MatPaginator>();
  @Input("pageIndex") set pageIndexChanged(pageIndex: number) {
    this.pageIndex = pageIndex;
  }
  @Input("length") set lengthChanged(length: number) {
    this.length = length;
    this.updateGoto();
  }
  @Input("pageSize") set pageSizeChanged(pageSize: number) {
    this.pageSize = pageSize;
    this.updateGoto();
  }

  constructor() {}

  ngOnInit() {
    this.updateGoto();
  }

  updateGoto() {
    this.goTo = (this.pageIndex || 0) + 1;
    this.pageNumbers = [];
    for (let i = 1; i <= Math.ceil(this.length / this.pageSize); i++) {
      this.pageNumbers.push(i);
    }
    //this.emitPageEvent();
  }

  paginationChange(pageEvt: PageEvent) {
    this.length = pageEvt.length;
    this.pageIndex = pageEvt.pageIndex;
    this.pageSize = pageEvt.pageSize;
    this.updateGoto();
    //this.emitPageEvent();
  }

  // goToChange() {
  //   this.paginator.pageIndex = this.goTo - 1;
  //   const event: PageEvent = {
  //     length: this.paginator.length,
  //     pageIndex: this.paginator.pageIndex,
  //     pageSize: this.paginator.pageSize
  //   };
  //   this.paginator.page.next(event);
  //   this.emitPageEvent();
  // }
  //
  // emitPageEvent() {
  //   this.page.next(this.paginator);
  // }

}
