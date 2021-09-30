package com.watashi.bookstore.facade;

import com.watashi.bookstore.domain.grafico.Dashboard;
import com.watashi.bookstore.strategy.livro.*;
import com.watashi.bookstore.strategy.telefone.ValidaDadosTelefone;
import com.watashi.bookstore.strategy.transacao_status_carta.AltararStatusLivro;
import com.watashi.bookstore.strategy.transacao_status_carta.ValidaDadosTransacaoStatusLivro;
import com.watashi.bookstore.strategy.carrinho.*;
import com.watashi.bookstore.strategy.cupom.*;
import com.watashi.bookstore.strategy.dashboard.ValidaDadosDashboard;
import com.watashi.bookstore.strategy.devolucao.EnviaEmailStatusDaDevolucao;
import com.watashi.bookstore.strategy.grupo_precificacao.ValidaDadosGrupoPrecificacao;
import com.watashi.bookstore.strategy.pedido.*;
import com.watashi.bookstore.strategy.transicao.CalculaValorTransicao;
import com.watashi.bookstore.strategy.transicao.GeraCodigoTransacaoTransicao;
import com.watashi.bookstore.strategy.transicao.RetiraQuantidadeItemDoPedido;
import com.watashi.bookstore.strategy.transicao.RetornaQuantidadeItemPedidoParaEstoque;
import com.watashi.bookstore.strategy.troca.*;
import com.watashi.bookstore.strategy.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.cartao_credito.ValidaDadosCartaoCredito;
import com.watashi.bookstore.strategy.cartao_credito.ValidaDataValidadeCartao;
import com.watashi.bookstore.strategy.cartao_credito.ValidaNumeroJaExiste;
import com.watashi.bookstore.strategy.endereco.ValidaDadosEndereco;
import com.watashi.bookstore.strategy.endereco.ValidaExistenciaCidade;
import com.watashi.bookstore.strategy.pessoa.ValidaDadosPessoa;
import com.watashi.bookstore.strategy.pessoa.ValidaExistenciaPessoa;
import com.watashi.bookstore.dao.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AbstractFachada {

    protected Map<String, IDAO> daos = new HashMap<>();

    protected Map<String, Map<String, List<IStrategy>>> regrasNegocio = new HashMap<>();

    /*
        Todas Persistence
     */

    @Autowired
    private PessoaDAO pessoaDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private TipoTelefoneDAO tipoTelefoneDAO;

    @Autowired
    private LivroDAO livroDAO;

    @Autowired
    private EnderecoDAO enderecoDAO;

    @Autowired
    private CartaoCreditoDAO cartaoCreditoDAO;

    @Autowired
    private BandeiraDAO bandeiraDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private CartegoriaLivroDAO cartegoriaLivroDAO;

    @Autowired
    private EstadoDAO estadoDAO;

    @Autowired
    private CidadeDAO cidadeDAO;

    @Autowired
    private CarrinhoDAO carrinhoDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private TransacaoDAO transacaoDAO;

    @Autowired
    private CupomDAO cupomDAO;

    @Autowired
    private DashboardDAO dashboardDAO;

    @Autowired
    private TransacaoStatusLivroDAO transacaoStatusLivroDAO;

    @Autowired
    private GrupoPrecificacaoDAO grupoPrecificacaoDAO;

    /*
        Todas Strategy
     */

    @Autowired
    private ValidaDadosPessoa validaDadosPessoa;

    @Autowired
    private ValidaDadosTelefone validaDadosTelefone;

    @Autowired
    private ValidaExistenciaPessoa validaExistenciaPessoa;

    @Autowired
    private ValidaDadosUsuario validaDadosUsuario;

    @Autowired
    private ValidaSenhasIguais validaSenhasIguais;

    @Autowired
    private ValidaExistenciaUsuario validaExistenciaUsuario;

    @Autowired
    private ValidaUsuarioAtivo validaUsuarioAtivo;

    @Autowired
    private GeraCodigoUsuario geraCodigoUsuario;

    @Autowired
    private CriptografaSenha criptografarSenha;

    @Autowired
    private ValidaSenhaUsuario validaSenhaUsuario;

    @Autowired
    private ValidaDadosLivro validaDadosLivro;

    @Autowired
    private GeraCodigoLivro geraCodigoLivro;

    @Autowired
    private ValidaQuantidadeEstoque validaQuantidadeEstoque;

    @Autowired
    private RetiraLivroNaoDisponivelDoCarrinho retiraLivroNaoDisponivelDoCarrinho;

    @Autowired
    private ValidaDadosTransacaoStatusLivro validaDadosTransacaoStatusLivro;

    @Autowired
    private InsereDataCadastro insereDataCadastro;

    @Autowired
    private AltararStatusLivro altararStatusLivro;

    @Autowired
    private ValidaDadosGrupoPrecificacao validaDadosGrupoPrecificacao;

    @Autowired
    private MoveImagem moveImagem;

    @Autowired
    private InativaLivroNaoDisponivel inativaLivroNaoDisponivel;

    @Autowired
    private InseriItemDisponivelParaEstoque inseriItemDisponivelParaEstoque;

    @Autowired
    private ValidaDadosEndereco validaDadosEndereco;

    @Autowired
    private ValidaExistenciaCidade validaExistenciaCidade;

    @Autowired
    private ValidaDadosCartaoCredito validaDadosCartaoCredito;

    @Autowired
    private ValidaDataValidadeCartao validaDataValidadeCartao;

    @Autowired
    private ValidaNumeroJaExiste validaNumeroJaExiste;

    @Autowired
    private ValidaDadosPedido validaDadosPedido;

    @Autowired
    private CalculaValorPedido calculaValorPedido;

    @Autowired
    private ValidaDadosCarrinho validaDadosCarrinho;

    @Autowired
    private VerificaProdutoInativoNoCarrinho verificaProdutoInativoNoCarrinho;

    @Autowired
    private PegaCarrinhoSeExistir pegaCarrinhoSeExistir;

    @Autowired
    private ValidaItemJaEstaNoCarrinho validaItemJaEstaNoCarrinho;

    @Autowired
    private ValidaQuantidadeItemDisponivel validaQuantidadeItemDisponivel;

    @Autowired
    private RetiraItemDisponivel retiraItemDisponivel;

    @Autowired
    private RetornaItemDisponivel retornaItemDisponivel;

    @Autowired
    private GeraCodigoPedido geraCodigoPedido;

    @Autowired
    private CalcularDataEntrega calcularDataEntrega;

    @Autowired
    private RetiraItemEstoque retiraItemEstoque;

    @Autowired
    private RetornaItemEstoque retornaItemEstoque;

    @Autowired
    private AtualizaItensPedidos atualizaItensPedidos;

    @Autowired
    private MudaStatusCupom mudaStatusCupom;

    @Autowired
    private EnviaEmailStatusDoPedido enviaEmailStatusDoPedido;

    @Autowired
    private EnviaEmailStatusDaDevolucao enviaEmailStatusDaDevolucao;

    @Autowired
    private ValidaDadosTroca validaDadosTroca;

    @Autowired
    private RetiraQuantidadeItemDoPedido retiraQuantidadeItemDoPedido;

    @Autowired
    private RetornaQuantidadeItemPedidoParaEstoque retornaQuantidadeItemPedidoParaEstoque;

    @Autowired
    private CalculaValorTransicao calculaValorTransicao;

    @Autowired
    private EnviaEmailStatusDaTroca enviaEmailStatusDaTroca;

    @Autowired
    private ValidaDadosCupom validaDadosCupom;

    @Autowired
    private GeraCodigoCupom geraCodigoCupom;

    @Autowired
    private ValidaDadosCupomConsulta validaDadosCupomConsulta;

    @Autowired
    private ValidaCupomAtivo validaCupomAtivo;

    @Autowired
    private ValidaExistenciaCupom validaExistenciaCupom;

    @Autowired
    private GeraCupomComValorRestante geraCupomComValorRestante;

    @Autowired
    private GeraCodigoTransacaoTransicao geraCodigoTransacaoTransicao;

    @Autowired
    private ValidaDadosDashboard validaDadosDashboard;


    public AbstractFachada(){
    }

    protected void inicializeMaps(){

        //------------------ Hash Classe e DAO -------------------------//

        daos.put(Pessoa.class.getName(), pessoaDAO);
        daos.put(Usuario.class.getName(), usuarioDAO);
        daos.put(TipoTelefone.class.getName(), tipoTelefoneDAO);
        daos.put(Livro.class.getName(), livroDAO);
        daos.put(Endereco.class.getName(), enderecoDAO);
        daos.put(CartaoCredito.class.getName(), cartaoCreditoDAO);
        daos.put(Pedido.class.getName(), pedidoDAO);
        daos.put(CategoriaLivro.class.getName(), cartegoriaLivroDAO);
        daos.put(Estado.class.getName(), estadoDAO);
        daos.put(Cidade.class.getName(), cidadeDAO);
        daos.put(Carrinho.class.getName(), carrinhoDAO);
        daos.put(Item.class.getName(), itemDAO);
        daos.put(Bandeira.class.getName(), bandeiraDAO);
        daos.put(Transicao.class.getName(), transacaoDAO);
        daos.put(Cupom.class.getName(), cupomDAO);
        daos.put(Dashboard.class.getName(), dashboardDAO);
        daos.put(TransacaoStatusLivro.class.getName(), transacaoStatusLivroDAO);
        daos.put(GrupoPrecificacao.class.getName(), grupoPrecificacaoDAO);

        //------------------------ Hash Pessoa ----------------------------//

        List<IStrategy> rnsPessoaSalvar = new ArrayList<>();

        rnsPessoaSalvar.add(validaDadosPessoa);
        rnsPessoaSalvar.add(validaDadosTelefone);
        rnsPessoaSalvar.add(validaExistenciaPessoa);
        rnsPessoaSalvar.add(validaDadosUsuario);
        rnsPessoaSalvar.add(validaSenhasIguais);
        rnsPessoaSalvar.add(validaExistenciaUsuario);
        rnsPessoaSalvar.add(geraCodigoUsuario);
        rnsPessoaSalvar.add(criptografarSenha);

        List<IStrategy> rnsPessoaAlterar = new ArrayList<>();

        rnsPessoaAlterar.add(validaDadosPessoa);
        rnsPessoaAlterar.add(validaDadosUsuario);
        rnsPessoaAlterar.add(validaExistenciaUsuario);

        Map<String,List<IStrategy>> mapaLeitor = new HashMap<>();

        mapaLeitor.put("SALVAR",rnsPessoaSalvar);
        mapaLeitor.put("ALTERAR",rnsPessoaAlterar);

        this.regrasNegocio.put(Pessoa.class.getName(), mapaLeitor);

        //------------------------ Hash Usuario ----------------------------//


        List<IStrategy> rnsUsuarioConsultar = new ArrayList<>();

        rnsUsuarioConsultar.add(validaDadosUsuario);
        rnsUsuarioConsultar.add(validaSenhaUsuario);
        rnsUsuarioConsultar.add(validaUsuarioAtivo);

        List<IStrategy> rnsUsuarioAlterar = new ArrayList<>();

        rnsUsuarioAlterar.add(validaSenhasIguais);
        rnsUsuarioAlterar.add(validaDadosUsuario);
        rnsUsuarioAlterar.add(criptografarSenha);

        Map<String, List<IStrategy>> mapaUsuario = new HashMap<>();

        mapaUsuario.put("CONSULTAR",rnsUsuarioConsultar);
        mapaUsuario.put("ALTERAR",rnsUsuarioAlterar);

        this.regrasNegocio.put(Usuario.class.getName(), mapaUsuario);

        //------------------------ Hash Carta ----------------------------//

        List<IStrategy> rnsLivroSalvar = new ArrayList<>();

        rnsLivroSalvar.add(validaDadosLivro);
        rnsLivroSalvar.add(moveImagem);
        rnsLivroSalvar.add(inseriItemDisponivelParaEstoque);
        rnsLivroSalvar.add(geraCodigoLivro);
        rnsLivroSalvar.add(insereDataCadastro);

        List<IStrategy> rnsLivroAlterar = new ArrayList<>();

        rnsLivroAlterar.add(validaDadosLivro);
        rnsLivroAlterar.add(moveImagem);
        rnsLivroAlterar.add(validaQuantidadeEstoque);
        rnsLivroAlterar.add(inativaLivroNaoDisponivel);
        rnsLivroAlterar.add(retiraLivroNaoDisponivelDoCarrinho);

        Map<String, List<IStrategy>> mapaLivro = new HashMap<>();

        mapaLivro.put("SALVAR",rnsLivroSalvar);
        mapaLivro.put("ALTERAR",rnsLivroAlterar);

        this.regrasNegocio.put(Livro.class.getName(), mapaLivro);

        //----------------- Hash TransacaoStatusCarta --------------------//

        List<IStrategy> rnsTransacaoStatusLivroSalvar = new ArrayList<>();

        rnsTransacaoStatusLivroSalvar.add(validaDadosTransacaoStatusLivro);
        rnsTransacaoStatusLivroSalvar.add(altararStatusLivro);

        List<IStrategy> rnsTransacaoStatusLivroAlterar = new ArrayList<>();

        rnsTransacaoStatusLivroAlterar.add(validaDadosTransacaoStatusLivro);

        Map<String, List<IStrategy>> mapaTransacaoStatusLivro = new HashMap<>();

        mapaTransacaoStatusLivro.put("SALVAR", rnsTransacaoStatusLivroSalvar);
        mapaTransacaoStatusLivro.put("ALTERAR", rnsTransacaoStatusLivroAlterar);

        this.regrasNegocio.put(TransacaoStatusLivro.class.getName(), mapaTransacaoStatusLivro);

        //------------------ Hash GrupoPrecificacao ----------------------//

        List<IStrategy> rnsGrupoPrecificacaoSalvar = new ArrayList<>();
        rnsGrupoPrecificacaoSalvar.add(validaDadosGrupoPrecificacao);

        List<IStrategy> rnsGrupoPrecificacaoAlterar = new ArrayList<>();
        rnsGrupoPrecificacaoAlterar.add(validaDadosGrupoPrecificacao);

        Map<String, List<IStrategy>> mapaGrupoPrecificacao = new HashMap<>();

        mapaGrupoPrecificacao.put("SALVAR", rnsGrupoPrecificacaoSalvar);
        mapaGrupoPrecificacao.put("ALTERAR", rnsGrupoPrecificacaoAlterar);

        this.regrasNegocio.put(GrupoPrecificacao.class.getName(), mapaGrupoPrecificacao);

        //----------------------- Hash Endereco --------------------------//

        List<IStrategy> rnsEnderecoSalvar = new ArrayList<>();

        rnsEnderecoSalvar.add(validaDadosEndereco);
        rnsEnderecoSalvar.add(validaExistenciaCidade);

        List<IStrategy> rnsEnderecoAlterar = new ArrayList<>();

        rnsEnderecoAlterar.add(validaDadosEndereco);

        Map<String, List<IStrategy>> mapaEndereco = new HashMap<>();

        mapaEndereco.put("SALVAR",rnsEnderecoSalvar);
        mapaEndereco.put("ALTERAR",rnsEnderecoAlterar);

        this.regrasNegocio.put(Endereco.class.getName(), mapaEndereco);

        //--------------------- Hash CartaoCredito -----------------------//

        List<IStrategy> rnsCartaoCreditoSalvar = new ArrayList<>();

        rnsCartaoCreditoSalvar.add(validaDadosCartaoCredito);
        rnsCartaoCreditoSalvar.add(validaDataValidadeCartao);
        rnsCartaoCreditoSalvar.add(validaNumeroJaExiste);

        Map<String, List<IStrategy>> mapaCartaoCredito = new HashMap<>();

        mapaCartaoCredito.put("SALVAR",rnsCartaoCreditoSalvar);

        this.regrasNegocio.put(CartaoCredito.class.getName(), mapaCartaoCredito);

        //------------------------ Hash Categoria --------------------------//

        Map<String, List<IStrategy>> mapaCategoria = new HashMap<>();

        regrasNegocio.put(CategoriaLivro.class.getName(), mapaCategoria);

        //------------------------ Hash Item --------------------------//

        List<IStrategy> rnsItemAlterar = new ArrayList<>();

        rnsItemAlterar.add(validaQuantidadeItemDisponivel);
        rnsItemAlterar.add(retiraItemDisponivel);

        List<IStrategy> rnsItemExcluir = new ArrayList<>();

        rnsItemExcluir.add(retornaItemDisponivel);

        Map<String, List<IStrategy>> mapaItem = new HashMap<>();

        mapaItem.put("EXCLUIR", rnsItemExcluir);

        this.regrasNegocio.put(Item.class.getName(), mapaItem);

        //------------------------ Hash Carrinho --------------------------//

        List<IStrategy> rnsCarrinhoSalvar = new ArrayList<>();

        rnsCarrinhoSalvar.add(validaDadosCarrinho);
        rnsCarrinhoSalvar.add(validaItemJaEstaNoCarrinho);
        rnsCarrinhoSalvar.add(pegaCarrinhoSeExistir);
        rnsCarrinhoSalvar.add(validaQuantidadeItemDisponivel);
        rnsCarrinhoSalvar.add(retiraItemDisponivel);

        List<IStrategy> rnsCarrinhoAlterar = new ArrayList<>();

        rnsCarrinhoAlterar.add(validaDadosCarrinho);
        rnsCarrinhoAlterar.add(validaQuantidadeItemDisponivel);
        rnsCarrinhoAlterar.add(retiraItemDisponivel);

        List<IStrategy> rnsCarrinhoConsultar = new ArrayList<>();

        rnsCarrinhoConsultar.add(verificaProdutoInativoNoCarrinho);

        Map<String, List<IStrategy>> mapaCarrinho = new HashMap<>();

        mapaCarrinho.put("SALVAR", rnsCarrinhoSalvar);
        mapaCarrinho.put("ALTERAR", rnsCarrinhoAlterar);
        mapaCarrinho.put("CONSULTAR", rnsCarrinhoConsultar);

        this.regrasNegocio.put(Carrinho.class.getName(), mapaCarrinho);

        //------------------------ Hash Pedido --------------------------//

        List<IStrategy> rnsPedidoSalvar = new ArrayList<>();

        rnsPedidoSalvar.add(validaDadosPedido);
        rnsPedidoSalvar.add(atualizaItensPedidos);
        rnsPedidoSalvar.add(calculaValorPedido);
        rnsPedidoSalvar.add(geraCodigoPedido);
        rnsPedidoSalvar.add(calcularDataEntrega);
        rnsPedidoSalvar.add(retiraItemEstoque);
        rnsPedidoSalvar.add(geraCupomComValorRestante);
        rnsPedidoSalvar.add(mudaStatusCupom);
        rnsPedidoSalvar.add(enviaEmailStatusDoPedido);

        List<IStrategy> rnsPedidoAlterar = new ArrayList<>();

        rnsPedidoAlterar.add(validaDadosPedido);
        rnsPedidoAlterar.add(retornaItemEstoque);
        rnsPedidoAlterar.add(enviaEmailStatusDoPedido);

        Map<String, List<IStrategy>> mapaPedido = new HashMap<>();

        mapaPedido.put("SALVAR",rnsPedidoSalvar);
        mapaPedido.put("ALTERAR",rnsPedidoAlterar);

        this.regrasNegocio.put(Pedido.class.getName(), mapaPedido);

        //------------------------ Hash Troca --------------------------//

        List<IStrategy> rnsTrocaSalvar = new ArrayList<>();

        rnsTrocaSalvar.add(validaDadosTroca);
        rnsTrocaSalvar.add(calculaValorTransicao);
        rnsTrocaSalvar.add(geraCodigoTransacaoTransicao);
        rnsTrocaSalvar.add(enviaEmailStatusDaTroca);
        rnsTrocaSalvar.add(retiraQuantidadeItemDoPedido);
        rnsTrocaSalvar.add(enviaEmailStatusDaDevolucao);

        List<IStrategy> rnsTrocaAlterar = new ArrayList<>();

        rnsTrocaAlterar.add(retiraQuantidadeItemDoPedido);
        rnsTrocaAlterar.add(retornaQuantidadeItemPedidoParaEstoque);
        rnsTrocaAlterar.add(enviaEmailStatusDaTroca);
        rnsTrocaAlterar.add(enviaEmailStatusDaDevolucao);

        Map<String, List<IStrategy>> mapaTroca = new HashMap<>();

        mapaTroca.put("SALVAR", rnsTrocaSalvar);
        mapaTroca.put("ALTERAR", rnsTrocaAlterar);

        regrasNegocio.put(Transicao.class.getName(), mapaTroca);

        //------------------------ Hash Cupom --------------------------//

        List<IStrategy> rnsCupomSalvar = new ArrayList<>();

        rnsCupomSalvar.add(geraCodigoCupom);
        rnsCupomSalvar.add(validaDadosCupom);

        List<IStrategy> rnsCupomConsultar = new ArrayList<>();

        rnsCupomConsultar.add(validaDadosCupomConsulta);
        rnsCupomConsultar.add(validaExistenciaCupom);
        rnsCupomConsultar.add(validaCupomAtivo);

        Map<String, List<IStrategy>> mapaCupom = new HashMap<>();

        mapaCupom.put("SALVAR", rnsCupomSalvar);
        mapaCupom.put("CONSULTAR", rnsCupomConsultar);

        regrasNegocio.put(Cupom.class.getName(), mapaCupom);

        //------------------------ Hash Dashboard ------------------------//

        List<IStrategy> rnsDashboardConsultar = new ArrayList<>();

        rnsDashboardConsultar.add(validaDadosDashboard);

        Map<String, List<IStrategy>> mapaDashboard = new HashMap<>();

        mapaDashboard.put("CONSULTAR", rnsDashboardConsultar);

        regrasNegocio.put(Dashboard.class.getName(), mapaDashboard);
    }
}
