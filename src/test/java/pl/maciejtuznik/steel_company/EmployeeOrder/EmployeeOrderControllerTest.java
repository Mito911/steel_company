package pl.maciejtuznik.steel_company.EmployeeOrder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeOrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeOrderService employeeOrderService;

    @InjectMocks
    private EmpoyeeOrderController employeeOrderController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeOrderController).build();
    }

    @Test
    public void testGetEmployeeOrderById_NotFound() throws Exception {
        when(employeeOrderService.findEmployeeOrderById(1)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/employeeOrder/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetEmployeeOrderById_Found() throws Exception {
        EmployeeOrderInfo employeeOrderInfo = new EmployeeOrderInfo("Marek", "Król", "Max-Stal", 10, "1mm S235", BigDecimal.valueOf(100.00));
        when(employeeOrderService.findEmployeeOrderById(1)).thenReturn(List.of(employeeOrderInfo));

        mockMvc.perform(get("/employeeOrder/1"))
                .andExpect(status().isOk());
    }

}

