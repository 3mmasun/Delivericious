### DDD 

- This is a repository for the DDD training.

#### Context
- This is for Delivericious food delivery app
- We learnt about the domain already on Event Storming session and identified bounded contexts. 
- This repository demonstrates usage of Tactical Pattern of DDD. 

##### Use cases: 
1. Add  tomato soup to Basket
2. Add **Sea Food salad** which costs **$12.00**
3. Add **3** chocolate ice cream to Basket which cost **4$** 
4. Remove **3** chocolate ice cream
5. Duplicate basket
6. Calculate total price
7. Max basket quantity should not exceed 100 (assume it refers to whole basket)
8. Save basket as hashmap <id, data>
9. if basket has more than 5 soup, suggest coupon DELIVERICIOUS_10 WITH 10% discount percentage if available
   (assuming available coupons as input, assuming tomato soup is is a SOUP category)
   (Implement in a new test case)

todo:
implement money
implement id for basket

Entity vs Value Object
- immutability
- identity, or id
- internal state change due to behavior

Aggregate
- aggregate root
- strong consistency within `transactional and consistency boundary`, eventual consistency across
- expose behavior
- protect internal state
- aggregate should be saved instead of individual entities

Repository
- separate data storage concern out of aggregate root
- aggregate root will only require changes when there's business rule changes
- diff vs springboot, repository in DDD is a concept, not just save 1 table but all related entities too

Domain Service
- orchestrate funtionality across numerous aggregates
- 