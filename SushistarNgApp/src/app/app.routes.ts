import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from '../components/menu/menu.component';
import {HomeComponent} from '../components/home/home.component';
import {ProductPageComponent} from '../components/product-page/product-page.component';
import {ProfilePageComponent} from '../components/profile-page/profile-page.component';
import {AuthGuard} from '../guards/auth.guards';
import {CartComponent} from '../components/cart/cart.component';
import { ProductRegistrationComponent } from '../components/product-registration/product-registration.component';


export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'menu', component: MenuComponent},
  {path: 'home', component: HomeComponent},
  {path: 'user', component: ProfilePageComponent, canActivate: [AuthGuard]},
  {path: 'product/:name', component: ProductPageComponent},
  {path: 'cart', component: CartComponent, canActivate: [AuthGuard],},
  	{
		path: 'productRegistration',
		component: ProductRegistrationComponent
	}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
