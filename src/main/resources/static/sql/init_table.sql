create table todo(
    id serial primary key
  , title text
  , importance integer
  , urgency integer
  , deadline date
  , done text
);

insert into todo( title, importance, urgency, deadline, done )
values( 'todo-1', 0, 0, '2020-10-01', 'N' );
insert into todo( title, importance, urgency, deadline, done )
values( 'todo-2', 0, 1, '2020-10-02', 'Y' );
insert into todo( title, importance, urgency, deadline, done )
values( 'todo-3', 1, 0, '2020-10-03', 'N' );
insert into todo( title, importance, urgency, deadline, done )
values( 'todo-4', 1, 1, '2020-10-04', 'Y' );

