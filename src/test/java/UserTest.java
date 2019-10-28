import com.bjsxt.jpa.SpringBootApplition;
import com.bjsxt.jpa.dao.*;
import com.bjsxt.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.OrderAdapter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApplition.class)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryByName userRepositoryByName;

    @Autowired
    private UserRepositoryQuery userRepositoryQuery;

    @Autowired
    private UserRepositoryPagingAndSorting userRepositoryPagingAndSorting;

    @Autowired
    private UserRepositoryJPASpecificationExecutor userRepositoryJPASpecificationExecutor;

    @Test
    public void test() {

        User user=new User();
        user.setAddress("北京");
        user.setAge(18);
        user.setMobile("666");
        userRepository.save(user);
    }

    @Test
    public void test1(){
        List<User> users;
        users=userRepositoryByName.findByAddress("深圳");
        System.out.println(users);

        users=userRepositoryByName.findByAddressAndAge("广州",50);
        System.out.println(users);

        users=userRepositoryByName.findByAddressAndAge("广州",20);
        System.out.println(users);

        users=userRepositoryByName.findByAddressLike("%州");
        System.out.println(users);

    }


    @Test
    @Transactional
    @Rollback(false)
    public void test2(){
        List<User> users;

        users=userRepositoryQuery.queryByAddressSQL("广州");
        System.out.println(users);
        userRepositoryQuery.UpdateByAddressSQL(50,"广州");
    }


    @Test
    public void PagingAndSortingRepository(){
        //Pageable:封装了分页的参数，当前页，每页显示的条数，当前页从0开始
        //PageRequest(page, size) page:当前页, size:每页显示的条数
        Pageable pageable = new PageRequest(0,2);
        Page<User> page = userRepositoryPagingAndSorting.findAll(pageable);
    }


    @Test
    public void JPASpecificationExecutorTest(){
        /**
         * Specification用于封装查询条件d
         */
        Specification<User> spc = new Specification<User>() {
            //Predicate封装单个查询条件

            /**
             *
             * @param root 查询对象的属性封装
             * @param criteriaQuery 封装了我们要执行查询中的各个部分信息，select、from等
             * @param criteriaBuilder 查询条件的构造器
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList();
                list.add(criteriaBuilder.equal(root.get("address"),"广州"));
                list.add(criteriaBuilder.equal(root.get("age"),30));
                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.or(list.toArray(arr));
            }
        };
        List<User> list = userRepositoryJPASpecificationExecutor.findAll(spc);
        for (User user:list){
            System.out.println(user);
        }
    }


    @Test
    public void test3(){
        Specification<User> spc = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                return cb.or(cb.equal(root.get("address"),"广州"), cb.equal(root.get("age"), 30));
            }
        };

        List<User> list = userRepositoryJPASpecificationExecutor.findAll(spc);
        for (User user:list){
            System.out.println(user);
        }
    }
}
