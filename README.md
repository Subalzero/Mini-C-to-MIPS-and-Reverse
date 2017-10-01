# Mini-C-to-MIPS-and-Reverse
Full Program with GUI

Use eclipse ide then compile.

Grammar rules for MIPS input:

    program -> stmts
  
    stmts -> stmt stmts
  
    stmt -> label: stmts |
  
            li  reg, reg, num |
            
            addi  reg, reg, num |
            
            andi  reg, reg, num |
            
            ori  reg, reg, num |
            
            xori  reg, reg, num |
            
            add  reg, reg, reg |
            
            sub  reg, reg, reg |
            
            mult reg, reg, reg |
            
            div  reg, reg, reg |
            
            and  reg, reg, reg |
            
            or  reg, reg, reg |
            
            xor  reg, reg, reg |
            
            j label |
            
            beq  reg, reg, label |
            
            bgt  reg, reg, label |
            
            blt  reg, reg, label |
            
            beqz  reg, label |
            
            bgtz  reg, label |
            
            bltz  reg, label

 Grammar rules for Mini C is based from the Dragon book.
 
 Refer 'Compilers: Principles, Techniques, and Tools'
 
     program -> block 
     
     block -> { decls stmts } 
     
     decls -> decls decl | E
     
     decl -> type id ; 
     
     type -> type [ num ] | basic
     
     stmts -> stmts stmt | E
     
     stmt - > loc = bool ;
     
           | if( bool ) stmt 
           
           | if( bool ) stmt else stmt
           
           | while( bool ) stmt
           
           | do stmt while ( bool ) ;
           
           | break ;
           
           | continue ;
           
           | block
           
     loc - > loc [ bool ] | id
     
     bool - > bool || join | join
     
     join - > join && equality | equality
     
     equality -> equality == rel | equality != rel | rel
     
     rel -> expr < expr | expr <= expr | expr >= expr | expr > expr | expr
     
     expr -> expr + term | expr - term | term
     
     term -> term * unary | term / unary | unary
     
     unary -> ! unary | -unary | factor
     
     factor -> ( bool ) | loc | num | real | true | false
     

