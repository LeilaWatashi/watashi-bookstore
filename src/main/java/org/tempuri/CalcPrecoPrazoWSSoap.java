/**
 * CalcPrecoPrazoWSSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface CalcPrecoPrazoWSSoap extends java.rmi.Remote {
    public CResultado calcPrecoPrazo(String nCdEmpresa, String sDsSenha, String nCdServico, String sCepOrigem, String sCepDestino, String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, String sCdAvisoRecebimento) throws java.rmi.RemoteException;
    public CResultado calcPrecoPrazoData(String nCdEmpresa, String sDsSenha, String nCdServico, String sCepOrigem, String sCepDestino, String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, String sCdAvisoRecebimento, String sDtCalculo) throws java.rmi.RemoteException;
    public CResultado calcPrecoPrazoRestricao(String nCdEmpresa, String sDsSenha, String nCdServico, String sCepOrigem, String sCepDestino, String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, String sCdAvisoRecebimento, String sDtCalculo) throws java.rmi.RemoteException;
    public CResultado calcPreco(String nCdEmpresa, String sDsSenha, String nCdServico, String sCepOrigem, String sCepDestino, String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, String sCdAvisoRecebimento) throws java.rmi.RemoteException;
    public CResultado calcPrecoData(String nCdEmpresa, String sDsSenha, String nCdServico, String sCepOrigem, String sCepDestino, String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, String sCdAvisoRecebimento, String sDtCalculo) throws java.rmi.RemoteException;
    public CResultado calcPrazo(String nCdServico, String sCepOrigem, String sCepDestino) throws java.rmi.RemoteException;
    public CResultado calcPrazoData(String nCdServico, String sCepOrigem, String sCepDestino, String sDtCalculo) throws java.rmi.RemoteException;
    public CResultado calcPrazoRestricao(String nCdServico, String sCepOrigem, String sCepDestino, String sDtCalculo) throws java.rmi.RemoteException;
    public CResultado calcPrecoFAC(String nCdServico, String nVlPeso, String strDataCalculo) throws java.rmi.RemoteException;
}
