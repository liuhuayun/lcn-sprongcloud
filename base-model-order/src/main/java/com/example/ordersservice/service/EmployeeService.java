package com.example.ordersservice.service;

import com.example.ordersservice.entity.Employee;

import java.util.List;

/**
 * (Employee)表服务接口
 *
 * @author makejava
 * @since 2020-02-22 10:00:22
 */
public interface EmployeeService {

    /**
     * 999
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Employee queryById(Integer id);




    public List<Employee> queryAllByLimit(int offset, int limit);
    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee insert(Employee employee);

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee update(Employee employee);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}