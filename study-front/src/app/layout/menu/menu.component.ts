import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { ConvertUtils } from 'src/app/common/utility/convert-utils';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.less']
})
export class MenuComponent implements OnInit {

  openKeyMap: Map<string, number>
  mode = false
  dark = false
  menus: any[]

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit() { this.getMenus() }
  getMenus() {
    this.authService.getMenuAndAuthoritys().subscribe((resMenu) => {
      let getMenus = resMenu.data.frontMenus;
      this.menus = ConvertUtils.menuConvert(0, getMenus);
      this.openKeyMap = ConvertUtils.openKeyMap;
      this.searchOpenKey();
    })
  }
  searchOpenKey() {
    for (let key of this.openKeyMap.keys()) {
      if (this.router.url.match(key)) {
        let openKey = this.openKeyMap.get(key);
        this.menus[openKey].open = true;
        return;
      }
    }
  }
}

