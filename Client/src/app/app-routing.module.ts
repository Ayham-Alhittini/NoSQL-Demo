import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Components
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { NotFoundComponent } from './components/error/not-found/not-found.component';
import { ServerErrorComponent } from './components/error/server-error/server-error.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { DepositComponent } from './components/transaction/deposit/deposit.component';
import { WithdrawComponent } from './components/transaction/withdraw/withdraw.component';
import { TransferComponent } from './components/transaction/transfer/transfer.component';
import { LoanComponent } from './components/loan/loan.component';
import { LoanApplyComponent } from './components/loan/loan-apply/loan-apply.component';
import { ProfileComponent } from './components/profile/profile.component';

// Guards
import { AuthGuard } from './guards/auth.guard';
import { IsGuestGuard } from './guards/is-guest.guard';
import { HistoryComponent } from './components/transaction/history/history.component';

const routes: Routes = [
  {
    path: '',
    canActivate: [AuthGuard],
    runGuardsAndResolvers: 'always',
    children: [
      {
        path: '',
        component: HomeComponent,
        pathMatch: 'full'
      },
      {
        path: 'transactions',
        component: TransactionComponent,
        children: [
          { path: 'deposit', component: DepositComponent },
          { path: 'withdraw', component: WithdrawComponent },
          { path: 'transfer', component: TransferComponent },
          { path: 'history', component: HistoryComponent },
        ]
      },
      {
        path: 'loans',
        component: LoanComponent,
        children: [
          { path: 'apply', component: LoanApplyComponent }
        ]
      },
      { path: 'profile', component: ProfileComponent }
    ]
  },
  {
    path: 'auth',
    canActivate: [IsGuestGuard],
    runGuardsAndResolvers: 'always',
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent }
    ]
  },
  { path: 'server-error', component: ServerErrorComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
