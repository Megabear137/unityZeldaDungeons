using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LinkController : MonoBehaviour
{

    float horizontal;
    float vertical;
    Animator animator;

    // Start is called before the first frame update
    void Start()
    {
        animator = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.GetAxis("Horizontal") > 0){
            horizontal = 1;
        }
        else if(Input.GetAxis("Horizontal") < 0){
            horizontal = -1;
        }
        else{
            horizontal = 0;
        }

        if(Input.GetAxis("Vertical") > 0){
            vertical = 1;
        }
        else if(Input.GetAxis("Vertical") < 0){
            vertical = -1;
        }
        else{
            vertical = 0;
        }

        animator.SetFloat("HorSpeed", horizontal);
        animator.SetFloat("VertSpeed", vertical);
    }

    void FixedUpdate() {
        Vector2 position = transform.position;
        if(horizontal != 0){
            position.x = position.x + 1.6f * (int)horizontal * Time.deltaTime;
        }
        else if(vertical != 0){
            position.y = position.y + 1.6f * (int)vertical * Time.deltaTime;
        }
        
        transform.position = position;
    }
}
