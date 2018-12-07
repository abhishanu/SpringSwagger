import { Component, OnInit } from '@angular/core';
import { CommonServiceService } from '../Services/common-service.service';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-list',
  templateUrl: 'list.page.html',
  styleUrls: ['list.page.scss']
})
export class ListPage implements OnInit {
  private selectedItem: any;
  public isItemLoaded: Boolean = false;
  public items: Array<{ title: string; note: string}> = [];
  constructor(private dataService: CommonServiceService, private nav: NavController) {
  }

  ngOnInit() {}

  ionViewWillEnter() {
   this.items = this.dataService.getList();
    if (this.items.length !== 0) {
      this.isItemLoaded = true;
    }
  }

  public onBackPressed() {
    console.log('Back Button Pressed');
    this.nav.goBack(true);
  }
}
