import { ErrorHandlerService } from './../../core/error-handler.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import * as moment from 'moment';

import { DashboardService } from './../dashboard.service';
import { PessoaService } from '../../pessoas/pessoa.service';
import { LazyLoadEvent } from 'primeng/api';
import { MessageService } from 'primeng/components/common/messageservice';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  dadosInicias: any[];

  constructor(
    private dashboardService: DashboardService,
    private errorHandler: ErrorHandlerService,
    private decimalPipe: DecimalPipe,
    private messageService: MessageService,
    private pessoaService: PessoaService) { }

  ngOnInit() {
    this.iniciarDados();
  }

  iniciarDados() {
    this.pessoaService.validarDadosInciais().then(totalPessoas => {
      if (totalPessoas > 0) {
        
      } else {
        this.adicionarDodosDoadores();
      }

    }).catch(erro => this.errorHandler.handle(erro));
  }


  adicionarDodosDoadores() {
    this.pessoaService.buscarDadosJson().then(dadosJson => {
      this.dadosInicias = dadosJson.map(c => ({
        nome: c.nome, cpf: c.cpf, rg: c.rg,
        data_nasc: c.data_nasc, sexo: c.sexo,
        mae: c.mae, pai: c.pai, email: c.email,
        cep: c.cep, endereco: c.endereco,
        numero: c.numero, bairro: c.bairro,
        cidade: c.cidade, estado: c.estado,
        telefone_fixo: c.telefone_fixo,
        celular: c.celular, altura: c.altura,
        peso: c.peso, tipo_sanguineo: c.tipo_sanguineo
      }));
    })
      .catch(erro => this.errorHandler.handle(erro));

    this.pessoaService.adicionarJson(this.dadosInicias)
      .then(pessoaAdicionada => {
        this.messageService.add({ severity: 'success', detail: 'Pessoas adicionadas com sucesso!' });
       
      })
      .catch(erro => this.errorHandler.handle(erro));
  }
}
