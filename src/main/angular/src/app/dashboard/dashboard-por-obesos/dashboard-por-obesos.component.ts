import { Title } from '@angular/platform-browser';
import { Component, OnInit, ViewChild } from '@angular/core';

import { LazyLoadEvent, ConfirmationService } from 'primeng/components/common/api';
import { MessageService } from 'primeng/components/common/messageservice';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { DashboardService, Filtro } from '../dashboard.service';

@Component({
  selector: 'app-dashboard-por-obesos',
  templateUrl: './dashboard-por-obesos.component.html',
  styleUrls: ['./dashboard-por-obesos.component.css']
})
export class DashboardPorPercentualObesosComponent implements OnInit {

  totalRegistros = 0;
  filtro = new Filtro();
  doadores = [];
  @ViewChild('tabela') grid;

  constructor(
    private dashboardService: DashboardService,
    private errorHandler: ErrorHandlerService,
    private title: Title
  ) { }

  ngOnInit() {
    this.title.setTitle('Pesquisa de Doadores por Estados');
    this.pesquisar();
  }

  pesquisar() {
    this.dashboardService.porPercentualDeObesos()
      .then(resultado => {
        this.totalRegistros = 0;
        this.doadores = resultado;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  

}
