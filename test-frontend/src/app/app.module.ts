import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MDBBootstrapModule} from 'angular-bootstrap-md';
import { BookCreateComponent } from './book-create/book-create.component';
import { CategoryCreateComponent } from './category-create/category-create.component';
import { WelcomeComponent } from './welcome/welcome.component';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import { CategoryUpdateComponent } from './category-update/category-update.component';
import { CategoryBooksComponent } from './category-books/category-books.component';
import { CategoryDeleteComponent } from './category-delete/category-delete.component';
const appRoutes: Routes = [
  {
    path: 'category-create',
    component: CategoryCreateComponent,
    data: { title: 'Category Create' }
  },
  {
    path: 'category-update',
    component: CategoryUpdateComponent,
    data: { title: 'Category Update' }
  },
  {
    path: 'category-delete',
    component: CategoryDeleteComponent,
    data: { title: 'Category Delete' }
  },
  {
    path: 'category-books',
    component: CategoryBooksComponent,
    data: { title: 'Books of Category' }
  },
  {
    path: 'welcome',
    component: WelcomeComponent,
    data: { title: 'welcome to app' }
  },
  { path: '',
    redirectTo: '/welcome',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    BookCreateComponent,
    CategoryCreateComponent,
    WelcomeComponent,
    CategoryUpdateComponent,
    CategoryBooksComponent,
    CategoryDeleteComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    AppRoutingModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
