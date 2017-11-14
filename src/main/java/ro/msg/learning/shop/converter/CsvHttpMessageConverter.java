package ro.msg.learning.shop.converter;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import ro.msg.learning.shop.models.ListParam;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class CsvHttpMessageConverter<T, L extends ListParam<T>>
    extends AbstractHttpMessageConverter<L> {

    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return ListParam.class.isAssignableFrom(clazz);
    }

    @Override
    protected L readInternal(Class<? extends L> clazz, HttpInputMessage inputMessage)
        throws IOException {
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        Class<T> t = toBeanType(clazz.getGenericSuperclass());
        strategy.setType(t);
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(new InputStreamReader(inputMessage.getBody())).withMappingStrategy(strategy).build();
        List<T> beanList = csvToBean.parse();
        try {
            L l = clazz.newInstance();
            l.setList(beanList);
            return l;
        } catch (Exception e) {
            throw new HttpMessageNotReadableException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void writeInternal(L l, HttpOutputMessage outputMessage)
        throws IOException {

        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(toBeanType(l.getClass().getGenericSuperclass()));

        OutputStreamWriter outputStream = new OutputStreamWriter(outputMessage.getBody());
        StatefulBeanToCsv<T> beanToCsv =
            new StatefulBeanToCsvBuilder(outputStream)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(strategy)
                .build();
        try {
            beanToCsv.write(l.getList());
            outputStream.close();
        } catch (Exception e) {
            throw new HttpMessageNotReadableException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> toBeanType(Type type) {
        return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }
}
