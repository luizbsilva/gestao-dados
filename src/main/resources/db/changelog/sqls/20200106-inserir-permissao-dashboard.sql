INSERT INTO permissao (codigo, descricao) values (24, 'ROLE_CADASTRAR_DASHBOARD');
INSERT INTO permissao (codigo, descricao) values (25, 'ROLE_REMOVER_DASHBOARD');
INSERT INTO permissao (codigo, descricao) values (26, 'ROLE_PESQUISAR_DASHBOARD');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 24);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 25);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 26);