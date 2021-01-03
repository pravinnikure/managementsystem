package com.management.managementsystem.Service;

import com.management.managementsystem.Model.Employee;
import com.management.managementsystem.api.EmployeResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeTest
{
    @Mock
        List<Employee> mockList;
    @InjectMocks
    EmployeService mockEmployee;

        @Before
        public void setUp()
        {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void employeeMockList()
        {
          //  when(mockList.get(1)).thenReturn("Raj");


            when(mockList.size()).thenReturn(2);
            assertEquals(2,mockList.size());

           assertEquals("Raj",mockEmployee.getEmployees().get(0).toString());

            assertEquals("Sham",mockEmployee.getEmployees().get(0).toString());

        }

}
