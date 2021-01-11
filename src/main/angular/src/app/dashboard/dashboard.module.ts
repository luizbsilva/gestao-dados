import { DashboardPorEstadosComponent } from './dashboard-por-estados/dashboard-por-estados.component';
import { InputMaskModule } from 'primeng/inputmask';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { DropdownModule } from 'primeng/dropdown';
import { SelectButtonModule } from 'primeng/selectbutton';
import { TooltipModule } from 'primeng/tooltip';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { NgModule } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';

import { PanelModule } from 'primeng/panel';
import { ChartModule } from 'primeng/chart';

import { SharedModule } from './../shared/shared.module';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { DashboardPorImcComponent } from './dashboard-por-imc/dashboard-por-imc.component';
import { DashboardPorTipoSanguineoMediaIdadeComponent } from './dashboard-por-tipo-media-idade/dashboard-por-tipo-media-idade.component';
import { DashboardPorPercentualObesosComponent } from './dashboard-por-obesos/dashboard-por-obesos.component';
import { DashboardPorQuantidadePorTipoSanguineoComponent } from './dashboard-quantidade-por-tipo-sanguineo/dashboard-quantidade-por-tipo-sanguineo.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    InputTextModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    SelectButtonModule,
    DropdownModule,
    CurrencyMaskModule,
    ProgressSpinnerModule,
    InputMaskModule,

    PanelModule,
    ChartModule,

    SharedModule,
    DashboardRoutingModule,


    SharedModule,
  ],
  declarations: [DashboardComponent, 
    DashboardPorEstadosComponent,
    DashboardPorImcComponent,
    DashboardPorPercentualObesosComponent,
    DashboardPorTipoSanguineoMediaIdadeComponent,
    DashboardPorQuantidadePorTipoSanguineoComponent],
  providers: [ DecimalPipe ]
})
export class DashboardModule { }
