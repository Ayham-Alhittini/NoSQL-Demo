import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavComponent } from './components/nav/nav.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { SharedModule } from './modules/shared.module';
import { NotFoundComponent } from './components/error/not-found/not-found.component';
import { ServerErrorComponent } from './components/error/server-error/server-error.component';
import { JwtInterceptor } from './interceptor/jwt.interceptor';
import { LoadingInterceptor } from './interceptor/loading.interceptor';
import { LoginComponent } from './components/auth/login/login.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { LoanComponent } from './components/loan/loan.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DepositComponent } from './components/transaction/deposit/deposit.component';
import { WithdrawComponent } from './components/transaction/withdraw/withdraw.component';
import { TransferComponent } from './components/transaction/transfer/transfer.component';
import { LoanApplyComponent } from './components/loan/loan-apply/loan-apply.component';
import { HistoryComponent } from './components/transaction/history/history.component';
import { NotificationsComponent } from './components/nav/notifications/notifications.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HomeComponent,
    RegisterComponent,
    NotFoundComponent,
    ServerErrorComponent,
    LoginComponent,
    TransactionComponent,
    LoanComponent,
    ProfileComponent,
    DepositComponent,
    WithdrawComponent,
    TransferComponent,
    LoanApplyComponent,
    HistoryComponent,
    NotificationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    SharedModule
  ],
  providers: [
    {provide : HTTP_INTERCEPTORS, useClass : JwtInterceptor, multi : true},
    {provide : HTTP_INTERCEPTORS, useClass : LoadingInterceptor, multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
