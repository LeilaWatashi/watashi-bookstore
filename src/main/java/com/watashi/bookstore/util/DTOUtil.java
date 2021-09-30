package com.watashi.bookstore.util;


import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.dto.IDTO;

public class DTOUtil {
    public static Resultado tranfereParaDTO(Resultado resultado, IDTO dto){
        if(Util.isNotNull(resultado.getEntidades()))
            resultado.getEntidades().replaceAll( entidade -> dto.parseEntityToDTO(entidade));
        return resultado;
    }
}
