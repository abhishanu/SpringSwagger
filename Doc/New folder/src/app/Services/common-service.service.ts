import { Injectable } from '@angular/core';

import {IonicStorageModule} from '@ionic/storage';

@Injectable({
  providedIn: 'root'
})
export class CommonServiceService {
  private items: Array<{ title: string; note: string}> = [];
  constructor(private storage: IonicStorageModule) { }

  public addItemToList(titleName: string , noteValue: string) {
    this.items.push({
      title: titleName,
      note: noteValue
    });
  }

  public deleteItemFromList() {
    console.log('Delete request');
  }

  public getList(): Array<{ title: string; note: string}> {
    return this.items;
  }
}
